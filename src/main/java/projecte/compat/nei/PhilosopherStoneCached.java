package projecte.compat.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class PhilosopherStoneCached extends TemplateRecipeHandler.CachedRecipe
{
	  public ArrayList<PositionedStack> ingredients;
	  public PositionedStack result;
	  private final boolean isHidden;

	  public PhilosopherStoneCached(PhilosopherStoneRecipeHandler philosopherstonerecipehandler, int width, int height, Object[] items, ItemStack out, boolean isHidden)
	  {
	    super(philosopherstonerecipehandler);
	    this.result = new PositionedStack(out, 119, 24);
	    this.ingredients = new ArrayList();
	    setIngredients(width, height, items);
	    this.isHidden = isHidden;
	  }


	  public void setIngredients(int width, int height, Object[] items)
	  {
	    for (int x = 0; x < width; x++)
	    {
	      for (int y = 0; y < height; y++)
	      {
	        if (items[(y * width + x)] == null)
	          continue;
	        PositionedStack stack;
	    
	        if (((items[(y * width + x)] instanceof List)) && (((List)items[(y * width + x)]).isEmpty())) {
	          stack = new PositionedStack(new ItemStack(Blocks.fire), 25 + x * 18, 6 + y * 18, false);
	        }
	        else
	        {
	          if ((items[(y * width + x)] instanceof RecipePart)) {
	            stack = new PositionedStack(((RecipePart)items[(y * width + x)]).getExamples(), 25 + x * 28, 6 + y * 18, false);
	          }
	          else
	          {
	            stack = new PositionedStack(items[(y * width + x)], 25 + x * 18, 6 + y * 18, false);
	          }
	        }

	        stack.setMaxSize(1);
	        this.ingredients.add(stack);
	      }
	    }
	  }

	  public List<PositionedStack> getIngredients()
	  {
	    return getCycledIngredients(20, this.ingredients);
	  }

	  public PositionedStack getResult()
	  {
	    return this.result;
	  }

	  public void computeVisuals()
	  {
	    for (PositionedStack p : this.ingredients) {
	      p.generatePermutations();
	    }

	    this.result.generatePermutations();
	  }
	}