/**
 * Created by outrun on 6/25/17.
 */
var requireDir = require('require-dir');
var api_routers = requireDir('./routers/api');

module.exports = function (app) {
  for (var router in api_routers) {
    app.use('/api', api_routers[router]);
  }
};
