package com.wittycode.poelevelinghelper.gemimport.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class GemInfoExtensionsTest {

    private val questToSearch = "Breaking some Eggs"
    val inputGemInfoSet = mutableSetOf(
            GemInfo("testGemNoTemplar", "", "Fallen From Grace",
                    mutableSetOf(AvailableClasses.SCION, AvailableClasses.WITCH, AvailableClasses.MARAUDER)),
            GemInfo("testGemWithTemplar", "", questToSearch,
                    mutableSetOf(AvailableClasses.SCION, AvailableClasses.TEMPLAR)
            )
    )
    val inputGemInfo = GemInfo("Storm Brand")
    val skillNamesWithoutGem = mutableSetOf("Galvanic Arrow", "Cleave")

    @Nested
    @DisplayName("AvailableForClass")
    inner class AvailableForClass {
        @Test
        fun `empty input set`() {
            val actual =
                    mutableSetOf<GemInfo>().filterForAvailableClass(AvailableClasses.TEMPLAR)
            assertThat(actual).isEmpty()
        }

        @Test
        fun `class included`() {
            val actual = inputGemInfoSet.filterForAvailableClass(AvailableClasses.SHADOW)
            assertThat(actual.map { it.gemName }).isEmpty()
        }

        @Test
        fun `class not included`() {
            val actual = inputGemInfoSet.filterForAvailableClass(AvailableClasses.TEMPLAR)
            assertThat(actual.map { it.gemName }).containsExactly("testGemWithTemplar")
        }
    }

    @Nested
    inner class FilterForQuest {
        @Test
        fun `empty set`() {
            val actual = mutableSetOf<GemInfo>().filterForQuest(questToSearch)
            assertThat(actual).isEmpty()
        }
        @Test
        fun `quest found`() {
            val actual = inputGemInfoSet.filterForQuest(questToSearch)
            assertThat(actual.map { it.questUnlock }).containsExactly(questToSearch)
        }
        @Test
        fun `quest not found`() {
            val actual = inputGemInfoSet.filterForQuest("no quest to find")
            assertThat(actual).isEmpty()
        }
    }

    @Nested
    inner class GemIsInBuild {
        @Test
        fun `empty set`() {
            assertThat(inputGemInfo.gemIsInBuild(emptySet())).isFalse()
        }
        @Test
        fun `gem not contained in set`() {
            assertThat(inputGemInfo.gemIsInBuild(skillNamesWithoutGem)).isFalse()
        }

        @Test
        fun `gem in set`() {
            skillNamesWithoutGem.add("Storm Brand")
            assertThat(inputGemInfo.gemIsInBuild(skillNamesWithoutGem)).isTrue()
        }
    }
}