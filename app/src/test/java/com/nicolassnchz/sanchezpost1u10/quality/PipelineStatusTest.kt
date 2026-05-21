package com.nicolassnchz.sanchezpost1u10.quality

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PipelineStatusTest {
    private val pipelineStatus = PipelineStatus()

    @Test
    fun coverageIsEnoughWhenItIsAtLeastSixty() {
        assertTrue(pipelineStatus.isCoverageEnough(60))
        assertTrue(pipelineStatus.isCoverageEnough(80))
    }

    @Test
    fun coverageIsNotEnoughWhenItIsBelowSixty() {
        assertFalse(pipelineStatus.isCoverageEnough(59))
    }

    @Test
    fun buildChannelIsReleaseForMainBranch() {
        assertEquals("release", pipelineStatus.buildChannel("main"))
    }

    @Test
    fun buildChannelIsValidationForOtherBranches() {
        assertEquals("validation", pipelineStatus.buildChannel("develop"))
    }
}