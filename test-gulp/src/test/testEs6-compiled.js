"use strict";

require("babel-polyfill");

function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } step("next"); }); }; } /**
                                                                                                                                                                                                                                                                                                                                                                                                                                                              * Created by outrun on 12/7/15.
                                                                                                                                                                                                                                                                                                                                                                                                                                                              */

var x = function x(n) {
  return n + 1;
};
console.log(x(1));
var y = (function () {
  var ref = _asyncToGenerator(regeneratorRuntime.mark(function _callee() {
    return regeneratorRuntime.wrap(function _callee$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            _context.next = 2;
            return 1;

          case 2:
          case "end":
            return _context.stop();
        }
      }
    }, _callee, this);
  }));

  return function y() {
    return ref.apply(this, arguments);
  };
})();
var z = regeneratorRuntime.mark(function z() {
  return regeneratorRuntime.wrap(function z$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return 1;

        case 2:
        case "end":
          return _context2.stop();
      }
    }
  }, z, this);
});

module.exports = exports = {
  x: x,
  y: y,
  z: z
};

//# sourceMappingURL=testEs6-compiled.js.map