/**
 * Created by outrun on 12/7/15.
 */
import 'babel-polyfill';

let x = n => n + 1;
console.log(x(1));
let y = async function () {
  "use strict";
  await 1;
};
let z = function* () {
  "use strict";
  yield 1;
};

module.exports = exports = {
  x: x,
  y: y,
  z: z
};
