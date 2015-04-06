/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.CustomizableGenerator;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public abstract class GeneratorOreBase extends CustomizableGenerator
{
    protected int amountPerChunk;
    protected int minHeight;
    protected int maxHeight;
    
    protected GeneratorOreBase() {}
    
    protected GeneratorOreBase(int amountPerChunk, int minHeight, int maxHeight)
    {
        this.amountPerChunk = amountPerChunk;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            BlockPos generatedPos = pos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
            
            generate(world, random, generatedPos);
        }
    }
    
    @Override
    public void writeToJson(JsonObject json, JsonSerializationContext context)
    {
        json.addProperty("amount_per_chunk", this.amountPerChunk);
        json.addProperty("min_height", this.minHeight);
        json.addProperty("max_height", this.maxHeight);
    }

    @Override
    public void readFromJson(JsonObject json, JsonDeserializationContext context)
    {
        this.amountPerChunk = json.get("amount_per_chunk").getAsInt();
        int minHeight = json.get("min_height").getAsInt();
        int maxHeight = json.get("max_height").getAsInt();
        
        validateMinMaxHeight(minHeight, maxHeight);
    }
    
    protected void validateMinMaxHeight(int minHeight, int maxHeight)
    {
        if (maxHeight < minHeight)
        {
            //Swap min and max height so that max is higher than min
            int prevMinHeight = minHeight;
            minHeight = maxHeight;
            maxHeight = prevMinHeight;
        }
        else if (maxHeight == minHeight)
        {
            if (minHeight < 255)
            {
                //Increase max height to be higher than min height
                ++maxHeight;
            }
            else
            {
                //Decrease min height so that max is higher
                --minHeight;
            }
        }
        
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
}