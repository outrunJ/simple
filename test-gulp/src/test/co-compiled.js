"use strict";

/**
 * Created by outrun on 12/7/15.
 */

var co = require('co');

var f = regeneratorRuntime.mark(function f() {
  var result;
  return regeneratorRuntime.wrap(function f$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.next = 2;
          return Promise.resolve(true);

        case 2:
          result = _context.sent;
          return _context.abrupt("return", result);

        case 4:
        case "end":
          return _context.stop();
      }
    }
  }, f, this);
});

co(f).then(function (val) {
  "use strict";

  console.log(val);
});

//# sourceMappingURL=co-compiled.js.map