package alveDoorsDesigner.cuttingList.table.tableItemFactory;

import alveDoorsDesigner.model.Door;
import alveDoorsDesigner.model.Module;
import alveDoorsDesigner.model.Wardrobe;
import alveDoorsDesigner.doorsDesigner.fulfillmentType.FulfillmentType;
import alveDoorsDesigner.cuttingList.table.model.TableItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RigidBars8Factory implements ListItemsFactory {
    @Override
    public List<TableItem> create(Wardrobe wardrobe) {
        RigidBarLengthCalculator calculator = new RigidBarLengthCalculator(new ModulePositionDetector());
        return create(wardrobe, calculator);
    }

    public List<TableItem> create(Wardrobe wardrobe, RigidBarLengthCalculator rigidBarLengthCalculator) {

        Map<Integer, Integer> rigidBars8 = new TreeMap<>();

        for (Door door : wardrobe.getDoors())
            for (Module module : door.getModules()) {
                if (module.getFulfillmentType().equals(FulfillmentType.BOARD)) {
                    int rigidBarLength = rigidBarLengthCalculator.calculate(door, module);
                    rigidBars8.merge(rigidBarLength, 2, Integer::sum);
                }
            }

        List<TableItem> items = new ArrayList<>();

        for (Map.Entry<Integer, Integer> rigidBar8 : rigidBars8.entrySet())
            items.add(new TableItem(
                    Product.RIGID_BAR_TT_8_MM_FILLINGS.getItemCode(),
                    Product.RIGID_BAR_TT_8_MM_FILLINGS.getName(),
                    Integer.toString(rigidBar8.getKey()),
                    rigidBar8.getValue()));

        return items;
    }
}
