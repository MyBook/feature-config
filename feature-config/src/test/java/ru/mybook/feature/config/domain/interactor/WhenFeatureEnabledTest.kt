package ru.mybook.feature.config.domain.interactor

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ru.mybook.common.test.randomBoolean
import ru.mybook.common.test.randomString

object WhenFeatureEnabledTest : Spek({

    describe("WhenFeatureEnabled") {

        val isFeatureEnabled: IsFeatureEnabled by memoized {
            mockk<IsFeatureEnabled>()
        }

        val whenFeatureEnabled: WhenFeatureEnabled by memoized {
            WhenFeatureEnabled(isFeatureEnabled)
        }

        it("when feature disabled action should never be called") {
            val featureName = randomString()
            val defaultValue = randomBoolean()
            every { isFeatureEnabled(featureName, defaultValue) } returns false
            val action: () -> Unit = mockk()
            whenFeatureEnabled(featureName, defaultValue, action)
            verify(exactly = 0) { action() }
        }

        it("when feature enabled action should be called once") {
            val featureName = randomString()
            val defaultValue = randomBoolean()
            every { isFeatureEnabled(featureName, defaultValue) } returns true
            val action: () -> Unit = mockk()
            every { action() } returns Unit
            whenFeatureEnabled(featureName, defaultValue, action)
            verify(exactly = 1) { action() }
        }

    }

})
