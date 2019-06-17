function solution(A) {
  let n = A.length
  let maxleft = [];
  maxleft[0] = A[0];
  for (let i = 1; i < n; ++i) {
    maxleft[i] = Math.max(maxleft[i - 1], A[i]);
  }
  let maxright = [];
  maxright[n - 2] = A[n - 1];
  for (let i = n - 2; i >= 0; --i) {
    maxright[i - 1] = Math.max(maxright[i], A[i]);
  }
  let ans = Math.abs(maxleft[0] - maxright[0]);
  for (let i = 1; i < n - 1; ++i) {
    ans = Math.max(ans, Math.abs(maxleft[i] - maxright[i]));
  }
  return ans;
}
function solution2(A) {
  let n = A.length
  let ans = A[0];
  for (let i = 1; i < n; ++i) {
    ans = Math.max(ans, A[i]);
  }
  return Math.max(ans - A[0], ans - A[n - 1]);
}

let x = solution([1, 2, 3])
console.log(x)