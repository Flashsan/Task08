package by.academy.it.task08.entity;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CarEntityTest extends Assert {

    CarEntity carActual = new CarEntity(2, "Lada 2103", "Brown", "700$");
    CarEntity carExpected = new CarEntity(2, "Lada 2103", "Brown", "700$");

    @Test
    public void EqualsCars() {
        assertThat("Mistake! Objects are not equal!", carActual, is(carExpected));
    }

    @Test
    public void EqualsToStringCars() {
        assertEquals(carActual.toString(), carExpected.toString());
    }
}
