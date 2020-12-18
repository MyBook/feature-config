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

object GetApplicationConfigStringTest : Spek({

    describe("GetApplicationConfigString") {

        val applicationConfig: ApplicationConfig = mockk()

        val getString: GetApplicationConfigString by memoized {
            GetApplicationConfigString(applicationConfig)
        }

        describe("should return a") {
            it("when ApplicationConfig return a") {
                val expected = "a"
                every { applicationConfig.getString(any()) } returns expected
                expect(getString("", "b")).toBe(expected)
            }
        }

        describe("should return b") {
            it("when ApplicationConfig return b") {
                val excepted = "b"
                every { applicationConfig.getString(any()) } returns excepted
                expect(getString("", "a")).toBe(excepted)
            }
        }

        describe("when ApplicationConfig has no value") {
            it("should return default value") {
                every { applicationConfig.getString(any()) } throws (NoValueException(""))
                expect(getString("", "a")).toBe("a")
                every { applicationConfig.getString(any()) } throws (NoValueException(""))
                expect(getString("", "b")).toBe("b")
            }
        }

        describe("when ApplicationConfig throws exception") {
            it("should throw exception") {
                class CustomException : Exception()
                every { applicationConfig.getString(any()) } throws CustomException()
                expect { getString("", "b") }.toThrow<CustomException>()
            }
        }

    }

})
