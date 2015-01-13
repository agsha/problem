package com.jsonfixture.pluggable;

import com.google.common.base.Optional;
import com.jsonfixture.AbstractTestCase;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;

public class ReferenceDetectorImplTest extends AbstractTestCase {

    @Test
    public void testDetectFirstReference_happyPath() {
        ReferenceDetectorImpl instance = injector.getInstance(ReferenceDetectorImpl.class);
        Optional<Position> positionOptional = instance.detectFirstReference("012345!reference012345");
        Assert.assertTrue(positionOptional.isPresent());
        Position pos = positionOptional.get();
        Assert.assertEquals(6, pos.getFrom());
    }

    @Test
    public void testDetectFirstReference_negativeCase() {
        ReferenceDetectorImpl instance = injector.getInstance(ReferenceDetectorImpl.class);
        Optional<Position> positionOptional = instance.detectFirstReference("012345reference012345");
        Assert.assertFalse(positionOptional.isPresent());
    }

    @Test
    public void testDetectAllReference_happyPath() {
        ReferenceDetectorImpl instance = injector.getInstance(ReferenceDetectorImpl.class);
        List<Position> list = instance.detectAllReferences("012345!aaa012345!bbb");
        Assert.assertEquals(2, list.size());
    }
}