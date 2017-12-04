package solonarv.mods.craftinghoppers.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.SoundType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import solonarv.mods.craftinghoppers.tileentity.TileEntityCraftingHopper;

public class BlockCraftingHopper extends BlockHopper {
    public BlockCraftingHopper() {
        super();
        this.setHardness(3.0F).setResistance(8.0F);
        this.setSoundType(SoundType.METAL).setUnlocalizedName("hopper");
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityCraftingHopper();
    }

    @Override
    protected Block setSoundType(SoundType sound) {
        return super.setSoundType(sound);
    }
}
