/**
 * Created by outrun on 12/2/15.
 */

var gulp = require('gulp'),
  sourcemaps = require('gulp-sourcemaps'),
  babel = require('gulp-babel'),
  gutil = require('gulp-util'),
  concat = require('gulp-concat');

gulp.task('default', function () {
  "use strict";
  return gulp.src('src/**/*.js')
    .pipe(sourcemaps.init())
    .pipe(babel())
    .on('error', gutil.log)
    .pipe(sourcemaps.write('.'/*, {
     includeContent: false,
     sourceRoot: '../src'
     }*/))
    .pipe(gulp.dest('dest'));
});

