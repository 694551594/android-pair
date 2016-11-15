package cn.yhq.pair;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.yhq.pair.item.CheckPairItem;
import cn.yhq.pair.item.PairCatalog;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.yhq.pair.test", appContext.getPackageName());


        new Pair.Builder(appContext)
                .addCatalog(
                        new PairCatalog()
                                .addItems(
                                        new CheckPairItem().setChecked(true)
                                )
                )
                .addCatalog(
                        new PairCatalog()
                                .addItems(
                                        new CheckPairItem().setChecked(true)
                                )
                ).build();
    }
}