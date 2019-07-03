if(!Detector.webgl) Detector.addGetWebGLMessage();
var SCREEN_HEIGHT = window.innerHeight,
    SCREEN_WIDTH = window.innerWidth;
var container, stats, scene, camera, renderer;
var mesh, light;
init();
animate();
function init(){
    container = document.getElementById('container');

    scene = new THREE.Scene();

    camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
    camera.position.z = 5;
    camera.lookAt(scene.position);

    var geometry = new THREE.BoxGeometry(1, 1, 1);
    var material = new THREE.MeshLambertMaterial({color: 0xffffff, shading: THREE.FlatShading});
    mesh = new THREE.Mesh(geometry, material);
    scene.add(mesh);

    light = new THREE.AmbientLight(0xff0000);
    scene.add(light);

    renderer = new THREE.WebGLRenderer({clearColor: 0x000000, clearAlpha: 1, antialias: false});
    renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    renderer.autoClear = false;
    container.appendChild(renderer.domElement);

    window.addEventListener('resize', onWindowResize, false);

}
function animate (){
    requestAnimationFrame(animate);
    mesh.rotation.x += 0.03;
    mesh.rotation.y += 0.03;
    renderer.render(scene, camera);
}

function onWindowResize(event){
    renderer.setSize(window.innerWidth, window.innerHeight);
    camera.aspect = window.innerWidth / window.innerHeight;
    // 重新产生矩阵
    camera.updateProjectionMatrix()
}
