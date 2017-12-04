package solonarv.mods.craftinghoppers.tileentity;

import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityCraftingHopper extends TileEntityHopper {
    @Override
    protected boolean updateHopper()
    {
        if (this.world != null && !this.world.isRemote)
        {
            if (!this.isOnTransferCooldown() && BlockHopper.isEnabled(this.getBlockMetadata()))
            {
                boolean flag = false;

                if (!this.isInventoryEmpty())
                {
                    flag = this.transferItemsOut();
                }

                if (!this.isFull())
                {
                    flag = pullItemsAndCheckCrafting(this) || flag;
                }

                if (flag)
                {
                    this.setTransferCooldown(8);
                    this.markDirty();
                    return true;
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }

    private boolean pullItemsAndCheckCrafting(TileEntityCraftingHopper hopper) {
        Boolean ret = net.minecraftforge.items.VanillaInventoryCodeHooks.extractHook(hopper);
        if (ret != null) return ret;
        //////////////////////////////////////////////////////////
        // BEGIN INSERTED CODE
        //////////////////////////////////////////////////////////

        if (hopper.getWorld().getBlockState(new BlockPos(hopper.getXPos(), hopper.getYPos(), hopper.getZPos())).getBlock() == Blocks.CRAFTING_TABLE) {
            if (pullCrafting(hopper))
                return true;
        }

        //////////////////////////////////////////////////////////
        // END INSERTED CODE
        //////////////////////////////////////////////////////////
        IInventory iinventory = getSourceInventory(hopper);

        if (iinventory != null)
        {
            EnumFacing enumfacing = EnumFacing.DOWN;

            if (isInventoryEmpty(iinventory, enumfacing))
            {
                return false;
            }

            if (iinventory instanceof ISidedInventory)
            {
                ISidedInventory isidedinventory = (ISidedInventory)iinventory;
                int[] aint = isidedinventory.getSlotsForFace(enumfacing);

                for (int i : aint)
                {
                    if (pullItemFromSlot(hopper, iinventory, i, enumfacing))
                    {
                        return true;
                    }
                }
            }
            else
            {
                int j = iinventory.getSizeInventory();

                for (int k = 0; k < j; ++k)
                {
                    if (pullItemFromSlot(hopper, iinventory, k, enumfacing))
                    {
                        return true;
                    }
                }
            }
        }
        else
        {
            for (EntityItem entityitem : getCaptureItems(hopper.getWorld(), hopper.getXPos(), hopper.getYPos(), hopper.getZPos()))
            {
                if (putDropInInventoryAllSlots((IInventory)null, hopper, entityitem))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean pullCrafting(TileEntityCraftingHopper hopper) {
        System.out.println("pullCrafting called!");
        return false;
    }
}
