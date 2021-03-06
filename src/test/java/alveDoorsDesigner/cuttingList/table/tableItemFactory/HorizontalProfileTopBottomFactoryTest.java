package alveDoorsDesigner.cuttingList.table.tableItemFactory;

import alveDoorsDesigner.model.Door;
import alveDoorsDesigner.model.Wardrobe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HorizontalProfileTopBottomFactoryTest {
    HorizontalProfileTopBottomFactory factory = new HorizontalProfileTopBottomFactory();
    Wardrobe wardrobe = new Wardrobe();

    int numberOfDoors = 3;
    int numberOfDivisions = 2;

    @Before
    public void setUp() {
        wardrobe.setCavityHeight(2000);
        wardrobe.setCavityWidth(2000);
        wardrobe.setNumberOfDoors(numberOfDoors);
    }

    @Test
    public void widthOfHorizontalProfileTopBottomShouldBeTheSameAsWidthOfModules() {
        assertEquals(Integer.toString(getWidthOfModule()), factory.create(wardrobe).getDimention());
    }

    @Test
    public void numberOfHorizontalProfileTopBottomShouldBeTwiceAsLargeAsTheNumberOfDoors() {
        for (Door door : wardrobe.getDoors()) {
            door.setAmountOfDivision(numberOfDivisions);
        }

        assertEquals(2 * numberOfDoors, factory.create(wardrobe).getQuantity());
    }

    private int getWidthOfModule() {
        return wardrobe.getDoors().get(0).getModules().get(0).getWidth();
    }
}