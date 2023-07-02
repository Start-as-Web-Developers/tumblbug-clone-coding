module.exports = {
  devServer: {
    proxy: {
      "/api": {
        target: "http://zangsu-backend.store",
        changeOrigin: true,
        secure: false,
      },
    },
    https: {
      key: "./localhost+1-key.pem", // ������ ������ �̸��� �Է����ּ���
      cert: "./localhost+1.pem",
    },
  },
  module: {
    rules: [
      {
        test: /\.scss$/,
        use: ["style-loader", "css-loader", "sass-loader"],
      },
    ],
  },
};
