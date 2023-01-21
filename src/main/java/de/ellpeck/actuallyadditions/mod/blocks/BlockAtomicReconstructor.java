/*
 * This file ("BlockAtomicReconstructor.java") is part of the Actually Additions mod for Minecraft.
 * It is created and owned by Ellpeck and distributed
 * under the Actually Additions License to be found at
 * http://ellpeck.de/actaddlicense
 * View the source code at https://github.com/Ellpeck/ActuallyAdditions
 *
 * © 2015-2017 Ellpeck
 */

package de.ellpeck.actuallyadditions.mod.blocks;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.ellpeck.actuallyadditions.api.lens.ILensItem;
import de.ellpeck.actuallyadditions.mod.ActuallyAdditions;
import de.ellpeck.actuallyadditions.mod.blocks.base.FullyDirectionalBlock;
import de.ellpeck.actuallyadditions.mod.config.CommonConfig;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityAtomicReconstructor;
import de.ellpeck.actuallyadditions.mod.util.AssetUtil;
import de.ellpeck.actuallyadditions.mod.util.Lang;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import de.ellpeck.actuallyadditions.mod.util.StringUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;

public class BlockAtomicReconstructor extends FullyDirectionalBlock.Container implements IHudDisplay {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final int NAME_FLAVOR_AMOUNTS_1 = 12;
    public static final int NAME_FLAVOR_AMOUNTS_2 = 14;

    public BlockAtomicReconstructor() {
        super(ActuallyBlocks.defaultPickProps(0, 10.0F, 80F));
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (this.tryToggleRedstone(world, pos, player)) {
            return ActionResultType.CONSUME;
        }
        if (!world.isClientSide) {
            TileEntityAtomicReconstructor reconstructor = (TileEntityAtomicReconstructor) world.getBlockEntity(pos);
            if (reconstructor != null) {
                if (!heldItem.isEmpty()) {
                    Item item = heldItem.getItem();
                    if (item instanceof ILensItem && reconstructor.inv.getStackInSlot(0).isEmpty()) {
                        ItemStack toPut = heldItem.copy();
                        toPut.setCount(1);
                        reconstructor.inv.setStackInSlot(0, toPut);
                        if (!player.isCreative()) {
                            heldItem.shrink(1);
                        }
                        return ActionResultType.CONSUME;
                    }
                } else {
                    ItemStack slot = reconstructor.inv.getStackInSlot(0);
                    if (!slot.isEmpty() && hand == Hand.MAIN_HAND) {
                        player.setItemInHand(hand, slot.copy());
                        reconstructor.inv.setStackInSlot(0, ItemStack.EMPTY);
                        return ActionResultType.CONSUME;
                    }
                }
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.CONSUME;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityAtomicReconstructor();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void displayHud(MatrixStack matrices, Minecraft minecraft, PlayerEntity player, ItemStack stack, RayTraceResult rayCast, MainWindow resolution) {
        if (!(rayCast instanceof BlockRayTraceResult) || minecraft.level == null) {
            return;
        }

        TileEntity tile = minecraft.level.getBlockEntity(((BlockRayTraceResult) rayCast).getBlockPos());
        if (tile instanceof TileEntityAtomicReconstructor) {
            ItemStack slot = ((TileEntityAtomicReconstructor) tile).inv.getStackInSlot(0);
            ITextComponent lens_name;
            if (slot.isEmpty()) {
                lens_name = new TranslationTextComponent("info.actuallyadditions.nolens");
            } else {
                lens_name = slot.getItem().getName(slot);

                AssetUtil.renderStackToGui(matrices, slot, resolution.getGuiScaledWidth() / 2 + 15, resolution.getGuiScaledHeight() / 2 - 19, 1F);
            }
            minecraft.font.drawShadow(matrices, lens_name.plainCopy().withStyle(TextFormatting.YELLOW).withStyle(TextFormatting.ITALIC).getString(), resolution.getGuiScaledWidth() / 2.0f + 35, resolution.getGuiScaledHeight() / 2.0f - 15, 0xFFFFFF);
        }
    }

    public static class TheItemBlock extends AABlockItem {

        private long lastSysTime;
        private int toPick1;
        private int toPick2;
        private final Block block;

        public TheItemBlock(Block blockIn) {
            super(blockIn, ActuallyBlocks.defaultBlockItemProperties);
            block = blockIn;
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public void appendHoverText(@Nonnull ItemStack pStack, @Nullable World pLevel, @Nonnull List<ITextComponent> pTooltip, @Nonnull ITooltipFlag pFlag) {
            super.appendHoverText(pStack, pLevel, pTooltip, pFlag);

            long sysTime = System.currentTimeMillis();

            if (this.lastSysTime + 3000 < sysTime) {
                this.lastSysTime = sysTime;
                if (Minecraft.getInstance().level != null) {
                    Random random = Minecraft.getInstance().level.random;
                    this.toPick1 = random.nextInt(NAME_FLAVOR_AMOUNTS_1) + 1;
                    this.toPick2 = random.nextInt(NAME_FLAVOR_AMOUNTS_2) + 1;
                }
            }

            String base = block.getDescriptionId() + ".info.";
            pTooltip.add(new TranslationTextComponent(base + "1." + this.toPick1).append(" ").append(new TranslationTextComponent(base + "2." + this.toPick2)).withStyle(s -> s.withColor(TextFormatting.GRAY)));

            if (pStack.hasTag() && pStack.getTag().contains("BlockEntityTag")) {
                CompoundNBT BET = pStack.getTag().getCompound("BlockEntityTag");
                int energy = 0;
                if (BET.contains("Energy")) {
                    energy = BET.getInt("Energy");
                }
                NumberFormat format = NumberFormat.getInstance();
                pTooltip.add(new TranslationTextComponent("misc.actuallyadditions.power_single", format.format(energy)));

                if (BET.contains("IsPulseMode")) {
                    pTooltip.add(new TranslationTextComponent("info.actuallyadditions.redstoneMode").append(": ")
                            .append(new TranslationTextComponent(BET.getBoolean("IsPulseMode")?"info.actuallyadditions.redstoneMode.pulse":"info.actuallyadditions.redstoneMode.deactivation").withStyle($ -> $.withColor(TextFormatting.RED))));
                }
            }
        }

        @Override
        protected boolean updateCustomBlockEntityTag(BlockPos pPos, World pLevel, @Nullable PlayerEntity pPlayer, ItemStack pStack, BlockState pState) {
            boolean ret = super.updateCustomBlockEntityTag(pPos, pLevel, pPlayer, pStack, pState);



            return ret;
        }
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, World world, BlockPos pos) {
        TileEntity t = world.getBlockEntity(pos);
        int i = 0;
        if (t instanceof TileEntityAtomicReconstructor) {
            i = ((TileEntityAtomicReconstructor) t).getEnergy();
        }
        return MathHelper.clamp(i / 20000, 0, 15);
    }
}
