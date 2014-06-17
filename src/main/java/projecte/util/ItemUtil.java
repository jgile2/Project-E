package projecte.util;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.common.base.Throwables;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUtil {
	public static boolean areItemsSameMatching(ItemStack item1, ItemStack item2) {
		if ((item1 == null) && (item2 == null))
			return true;
		if ((item1 == null) || (item2 == null))
			return false;
		
		return true;
	}

	public static ArrayList<ItemStack> getItemsFromClass(Class<?> claSS) {
		ArrayList list = new ArrayList();
		Iterator it = Item.itemRegistry.iterator();
		while (it.hasNext()) {
			Item item = (Item) it.next();
			if ((item != null) && (claSS.isInstance(item))) {
				list.add(new ItemStack(item, 1, 32767));
			}
		}

		it = Block.blockRegistry.iterator();
		while (it.hasNext()) {
			Block block = (Block) it.next();
			if ((block != null) && (claSS.isInstance(block))) {
				list.add(new ItemStack(block, 1, 32767));
			}
		}

		return list;
	}

	public enum ItemMatchCriteria {
		NBT(ItemMatchCriteria.MatchNBT.class), 
		ID(ItemMatchCriteria.MatchID.class),
		CLASS(ItemMatchCriteria.MatchCLASS.class),
		DAMAGE(ItemMatchCriteria.MatchDAMAGE.class);
		//WILDCARD(ItemMatchCriteria.MatchWILDCARD.class);

		public ItemMatchCriteria.ICompareItems comparer;

		 ItemMatchCriteria(Class<? extends ItemUtil.ItemMatchCriteria.ICompareItems> clazz) {
			try {
				this.comparer = ((ItemUtil.ItemMatchCriteria.ICompareItems) clazz.newInstance());
			} catch (Exception e) {
				Throwables.propagate(e);
			}
		}

		private boolean checkFor(ItemStack item1, ItemStack item2) {
			return this.comparer.checkFor(item1, item2);
		}

		public abstract interface ICompareItems {
			public abstract boolean checkFor(ItemStack paramItemStack1, ItemStack paramItemStack2);
		}

		public class MatchNBT implements ItemUtil.ItemMatchCriteria.ICompareItems {
			public boolean checkFor(ItemStack item1, ItemStack item2) {
				return ItemStack.areItemStackTagsEqual(item1, item2);
			}
		}

		public class MatchID implements ItemUtil.ItemMatchCriteria.ICompareItems {
			public boolean checkFor(ItemStack item1, ItemStack item2) {
				return Item.itemRegistry.getNameForObject(item1.getItem()) == Item.itemRegistry.getNameForObject(item2.getItem());
			}

		}

		public class MatchCLASS implements ItemUtil.ItemMatchCriteria.ICompareItems {
			public boolean checkFor(ItemStack item1, ItemStack item2) {
				return item1.getItem().getClass().equals(item2.getItem().getClass());
			}
		}
		public class MatchDAMAGE implements ItemUtil.ItemMatchCriteria.ICompareItems
		{
		  public boolean checkFor(ItemStack item1, ItemStack item2)
		  {
		    return item1.getItemDamage() == item2.getItemDamage();
		  }
		}
//		public class MatchWILDCARD
//		  implements ItemUtil.ItemMatchCriteria.ICompareItems
//		{
//		  public boolean checkFor(ItemStack item1, ItemStack item2)
//		  {
//		    return (ItemUtil.ItemMatchCriteria.access$000(ItemUtil.ItemMatchCriteria.ID, item1, item2)) && ((ItemUtil.ItemMatchCriteria.access$000(ItemUtil.ItemMatchCriteria.DAMAGE, item1, item2)) || (item1.func_77960_j() == 32767) || (item2.func_77960_j() == 32767));
//		  }
//		}
//	}
	}
}
