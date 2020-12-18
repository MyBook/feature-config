package ru.mybook.feature.config.domain.interactor

import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.expect
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import ru.mybook.feature.config.domain.ApplicationConfig
import ru.mybook.feature.config.domain.NoValueException

object GetApplicationConfigBooleanTest : Spek({

    describe("GetApplicationConfigBoolean") {

        val applicationConfig: ApplicationConfig = mockk()

        val getBoolean: GetApplicationConfigBoolean by memoized {
            GetApplicationConfigBoolean(applicationConfig)
        }

        describe("should return true") {
            it("when ApplicationConfig return true") {
                every { applicationConfig.getBoolean(any()) } returns true
                expect(getBoolean("", false)).toBe(true)
            }
        }

        describe("should return false") {
            it("when ApplicationConfig return false") {
                every { applicationConfig.getBoolean(any()) } returns false
                expect(getBoolean("", true)).toBe(false)
            }
        }

        describe("when ApplicationConfig has no value") {
            it("should return default value") {
                every { applicationConfig.getBoolean(any()) } throws (NoValueException(""))
                expect(getBoolean("", true)).toBe(true)
                every { applicationConfig.getBoolean(any()) } throws (NoValueException(""))
                expect(getBoolean("", false)).toBe(false)
            }
        }

        describe("when ApplicationConfig throws exception") {
            it("should throw exception") {
                class CustomException : Exception()
                every { applicationConfig.getBoolean(any()) } throws CustomException()
                expect { getBoolean("", false) }.toThrow<CustomException>()
            }
        }

    }

})
