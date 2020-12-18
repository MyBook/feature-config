package ru.mybook.feature.config.domain.interactor

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ru.mybook.common.test.randomBoolean
import ru.mybook.common.test.randomString

object IsFeatureEnabledTest : Spek({

    describe("IsFeatureEnabled") {

        val getApplicationConfigBoolean: GetApplicationConfigBoolean by memoized {
            mockk<GetApplicationConfigBoolean>()
        }

        val isFeatureEnabled: IsFeatureEnabled by memoized {
            IsFeatureEnabled(getApplicationConfigBoolean)
        }

        it("should add `_enabled` to feature name automatically") {
            val featureName = randomString()
            val defaultValue = randomBoolean()
            val expectedValue = randomBoolean()
            every {
                getApplicationConfigBoolean("${featureName}_enabled", defaultValue)
            } returns expectedValue
            expect(isFeatureEnabled(featureName, defaultValue)).toBe(expectedValue)
        }

        it("should throw same exception when GetApplicationConfigBoolean throws exception") {

            class CustomException : Exception()

            val defaultValue = randomBoolean()
            every { getApplicationConfigBoolean(any(), defaultValue) } throws CustomException()
            expect {
                isFeatureEnabled(
                    randomString(),
                    defaultValue,
                )
            }.toThrow<CustomException>()
        }

    }

})
