var webpack = require('webpack')
module.exports = {
  devtool: 'inline-source-map',
  entry: ['webpack/hot/dev-server', __dirname + '/app/main.js'],
  output: {
    path: __dirname + '/build',
    filename: 'bundle.js',
    publicPath: '/'
  },
  module: {
    loaders: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        loader: 'babel-loader'
      }
    ]
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin()
  ],
  devServer: {
    contentBase: __dirname + '/build',
    historyApiFallback: true,
    inline: true,
    port: 3031,
  }
}