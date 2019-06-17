/**
 * Created by outrun on 6/25/17.
 */
/**
 * App Main Entrance
 */
var express = require('express'),
  path = require('path'),
  connect = require('connect'),
  bodyParser = require('body-parser'),
  jsonParser = bodyParser.json(),
  xmlparser = require('express-xml-bodyparser'),
  serveStatic = require('serve-static'),
  router = require('./server/router.js')

var app = express();

app.use(serveStatic(__dirname + '/client'));
app.use(bodyParser.urlencoded({ extended: false }))

//set app routers
router(app);

app.listen(8080);
console.log("server started at 8080")
