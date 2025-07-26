module.exports = {
    root: true,
    env: {
      node: true,
    },
    extends: [
      'eslint:recommended',
      'plugin:vue/vue3-essential',
      'plugin:prettier/recommended',
    ],
    parserOptions: {
      ecmaVersion: 2020,
    },
    rules: {
      // 필요에 따라 규칙 추가/수정
    },
  };