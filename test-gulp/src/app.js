/**
 * Created by outrun on 12/2/15.
 */

var g = function* () {
  "use strict";
  yield 1;
};
var gg = g();
console.log(gg.next());

//var x = function ({x=1}) {
//  "use strict";
//  console.log(x);
//};
//x();

//var f = async function () {
//  "use strict";
//  return 1;
//};
//console.log(f());
