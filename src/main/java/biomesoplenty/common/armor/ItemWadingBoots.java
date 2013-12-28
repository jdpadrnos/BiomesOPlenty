package biomesoplenty.common.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class ItemWadingBoots extends ItemArmor
{
	public int textureID = 0;

	public ItemWadingBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par2EnumArmorMaterial, par3, par4);
		textureID = par4;
		setMaxDamage(0);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.getItem() == BOPItemHelper.get("wadingBoots"))
			return "biomesoplenty:textures/armor/wadingboots.png";
		
		return null;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 3) { itemIcon = iconRegister.registerIcon("biomesoplenty:wadingboots"); }
	}
}
