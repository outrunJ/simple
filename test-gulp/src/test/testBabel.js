/**
 * Created by outrun on 12/7/15.
 */
require('babel-core/register');
var obj = require('./testEs6');

console.log(obj.x(2));

obj.y().then(function (data) {
  "use strict";
  console.log(data);
});

var zz = obj.z();
console.log(zz.next());
