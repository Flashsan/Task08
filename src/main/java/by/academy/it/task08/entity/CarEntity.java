package by.academy.it.task08.entity;

import by.academy.it.task08.annatation.MyColumn;
import by.academy.it.task08.annatation.MyTable;

import java.util.Objects;

/**
 *
 */

@MyTable(name = "car")
public class CarEntity {
    /**
     *
     */
    @MyColumn(name = "id")
    private Integer id;
    /**
     *
     */
    @MyColumn(name = "model")
    private String model;
    /**
     *
     */
    @MyColumn(name = "color")
    private String color;
    /**
     *
     */
    @MyColumn(name = "coast")
    private String coast;

    /**
     * @param idOwn
     * @param modelOWn
     * @param colorOWn
     * @param coastOWn
     */
    public CarEntity(final Integer idOwn,
                     final String modelOWn,
                     final String colorOWn,
                     final String coastOWn) {
        this.id = idOwn;
        this.model = modelOWn;
        this.color = colorOWn;
        this.coast = coastOWn;
    }

    /**
     * @param modelOWn
     * @param colorOWn
     * @param coastOWn
     */
    public CarEntity(final String modelOWn,
                     final String colorOWn,
                     final String coastOWn) {
        this.model = modelOWn;
        this.color = colorOWn;
        this.coast = coastOWn;
    }

    /**
     * @param o
     * @return -
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarEntity car = (CarEntity) o;
        return Objects.equals(id, car.id)
                && Objects.equals(model, car.model)
                && Objects.equals(color, car.color)
                && Objects.equals(coast, car.coast);
    }

    /**
     * @return - hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, model, color, coast);
    }

    /**
     * @return -
     */
    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", model='" + model + '\''
                + ", color='" + color + '\''
                + ", coast=" + coast + '\''
                + '}' + '\n';
    }
}
