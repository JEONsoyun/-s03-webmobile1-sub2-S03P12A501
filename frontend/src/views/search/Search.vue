<template>
  <div id="app">
    <v-app id="inspire">
      <v-main>
        <v-container fluid>
          <v-row align="center">
            <v-col cols="12" sm="6">
              <v-select
                dense
                solo
                v-model="searchData.selected"
                :items="selects"
                label="Select"
                chips
                hint="검색 기준을 선택해 보세요"
                persistent-hint
              ></v-select>
            </v-col>
          </v-row>
        </v-container>
        <div>
          <v-text-field
            outlined
            label="검색어를 입력하세요"
            :rules="searchrules"
            hide-details="auto"
            v-model="searchData.word"
            clearable
            prepend-inner-icon="mdi-magnify"
            @keypress.enter="fetchsearchPost(searchData)"
          ></v-text-field>
        </div>
      </v-main>
    </v-app>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "Search",
  data() {
    return {
      selects: ["all", "user", "title", "content", "tag"],
      searchData: { selected: "", word: "" },
      searchrules: [
        (value) => !!value || "Required.",
        (value) => (value && value.length >= 2) || "2글자 이상 입력해주세요",
      ],
    };
  },
  methods: {
    ...mapActions(["fetchsearchPost"]),
  },
};
</script>
