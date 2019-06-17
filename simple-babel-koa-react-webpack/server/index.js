import Koa from 'koa'
import serve from 'koa-static'
import staticCache from 'koa-static-cache'
import log from '../lib/log'
import config from 'config-lite'
import convert from 'koa-convert'
import body from 'koa-body'
import koaWebpack from 'koa-webpack'
import webpack from 'webpack'
import webpackConfig from '../client/webpack.config'

const app = new Koa()
const logger = log.getLogger('server')
// app.use(body)
// app.use(convert(staticCache(config.publicPath)))
app.use(serve(config.publicPath))

if (config.isDev) {
  let compiler = webpack(webpackConfig)
  app.use(koaWebpack({
    compiler: compiler
  }))
}


if (module.parent) {
  module.exports = app
} else {
  logger.error('need to require this server and use.')
}