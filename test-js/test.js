/**
 * Created by outrun on 12/30/17.
 */
function* f() {
  yield 2
}
function* gen() {
  yield Promise.resolve(2)
}
Promise.resolve(function () {
  console.log(3)
})
let g =gen()
console.log(g.next())
console.log(1)