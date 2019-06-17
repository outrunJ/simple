/**
 * Created by outrun on 12/7/15.
 */

var co = require('co');

var f = function* () {
  "use strict";
  var result = yield Promise.resolve(true);
  return result;
};

co(f).then(function (val) {
  "use strict";
  console.log(val);
});