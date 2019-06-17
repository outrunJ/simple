'use strict';

/**
 * Created by outrun on 12/7/15.
 */

var babelCore = require('babel-core');

var es5Code = 'let x = n => n + 1';
var es6Code = babelCore.transform(es5Code, { presets: ['es2015'] }).code;

console.log(es6Code);

//# sourceMappingURL=codeTranslater-compiled.js.map