import server from './server/index'
import config from 'config-lite'
import log from './lib/log'

const logger = log.getLogger('app')

if (config.isIt) {
  module.exports = server
} else {
  server.listen(config.port, () => {
    logger.debug('server listening on: ', config.port)
  })
}


