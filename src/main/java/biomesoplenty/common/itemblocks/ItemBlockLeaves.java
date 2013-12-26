package biomesoplenty.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import biomesoplenty.common.blocks.BlockBOPLeaves;

public class ItemBlockLeaves extends ItemBlock
{
	public ItemBlockLeaves(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta | 4;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		BlockBOPLeaves block = (BlockBOPLeaves)Block.blocksList[itemStack.itemID];
		return super.getUnlocalizedName() + "." + block.getLeafType(itemStack.getItemDamage());
	}
}