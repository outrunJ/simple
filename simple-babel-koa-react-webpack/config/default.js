import path from 'path'
const ROOT_PATH = path.resolve(__dirname, '..')

module.exports = {
  port: 3030,
  publicPath: path.resolve(ROOT_PATH, 'client/build')
}

