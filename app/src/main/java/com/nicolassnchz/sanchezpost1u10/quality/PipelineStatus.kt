package com.nicolassnchz.sanchezpost1u10.quality

class PipelineStatus {
    fun isCoverageEnough(percentage: Int): Boolean {
        return percentage >= 60
    }

    fun buildChannel(branch: String): String {
        return if (branch == "main") "release" else "validation"
    }
}