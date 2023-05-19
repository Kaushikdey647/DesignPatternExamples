import org.example.Singleton.SingletonExample.SingletonEager;
import org.example.Singleton.SingletonExample.SingletonLazy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingletonExampleTest {

    @Test
    public void checkEqualityOnEagerInstance(){

        SingletonEager instance1 = SingletonEager.getInstance();

        SingletonEager instance2 = SingletonEager.getInstance();

        assertEquals(instance1, instance2);
    }

    @Test
    public void checkEqualityOnLazyInstance(){

        SingletonLazy instance1 = SingletonLazy.getInstance();

        SingletonLazy instance2 = SingletonLazy.getInstance();

        assertEquals(instance1, instance2);
    }
}
