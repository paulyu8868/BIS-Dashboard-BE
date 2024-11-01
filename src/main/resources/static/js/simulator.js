// 전역 변수 선언
let map;
let busMarkers = new Map();
let stopMarkers = [];
let routeLine = null;
let updateInterval;

// 노선별 ROUTE_ID
const ROUTE_ID_MAP = {
    '1': '307000010',
    '2': '307000020',
    '3': '307000080',
    '4': '307000020',
    '5': '307000080',
    '6': '307000020',
    '7': '307000010',
    '8': '307000080',
    '9': '307000020',
    '10': '307000010'
};

// 카카오맵 초기화
function initMap() {
    return new Promise((resolve, reject) => {
        kakao.maps.load(() => {
            try {
                const container = document.getElementById('map');
                const options = {
                    center: new kakao.maps.LatLng(35.967, 126.736), // 군산 중심 좌표
                    level: 5
                };
                map = new kakao.maps.Map(container, options);
                resolve();
            } catch (error) {
                console.error('지도 초기화 실패:', error);
                reject(error);
            }
        });
    });
}

// 정류장 마커 생성
function createBusStopMarker(position, name) {
    const content = document.createElement('div');
    content.className = 'bus-stop-marker';
    content.textContent = 'B';  // 모든 정류장을 'B'로 표시

    const marker = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        yAnchor: 1,
        zIndex: 1
    });

    const infoContent = document.createElement('div');
    infoContent.className = 'bus-stop-info';
    infoContent.textContent = name;

    const infowindow = new kakao.maps.CustomOverlay({
        position: position,
        content: infoContent,
        yAnchor: 2.5,
        zIndex: 2
    });

    // 마커에 마우스오버 이벤트 추가
    content.addEventListener('mouseover', () => {
        infowindow.setMap(map);
        content.style.transform = 'scale(1.2)';
    });

    // 마커에 마우스아웃 이벤트 추가
    content.addEventListener('mouseout', () => {
        infowindow.setMap(null);
        content.style.transform = 'scale(1)';
    });

    return {marker, infowindow};
}

// 버스 마커 생성
function createBusMarker(position, obuId) {
    const content = document.createElement('div');
    content.className = 'bus-marker';
    content.textContent = obuId;

    return new kakao.maps.CustomOverlay({
        position: position,
        content: content,
        zIndex: 3
    });
}

// 정류장 정보 가져오기
async function getBusStops(routeId) {
    try {
        const response = await fetch(`/api/simulator/route/${routeId}/stops`);
        if (!response.ok) throw new Error('정류장 정보를 가져오는데 실패했습니다.');
        return await response.json();
    } catch (error) {
        console.error('정류장 정보 조회 실패:', error);
        throw error;
    }
}

// 버스 위치 정보 가져오기
async function getBusLocation(obuId, routeId) {
    try {
        const response = await fetch(`/api/simulator/bus-location?obuId=${obuId}&routeId=${routeId}`);
        if (!response.ok) throw new Error('버스 위치 정보를 가져오는데 실패했습니다.');
        return await response.json();
    } catch (error) {
        console.error('버스 위치 조회 실패:', error);
        throw error;
    }
}

// 노선 및 정류장 표시
async function displayBusStops(routeNumber) {
    try {
        // 기존 정류장 마커와 라인 제거
        resetBusStops();

        const routeId = ROUTE_ID_MAP[routeNumber];
        const busStops = await getBusStops(routeId);

        const linePath = [];
        let latMin = 90, latMax = -90, lngMin = 180, lngMax = -180;

        // 정류장 마커 생성
        busStops.forEach(stop => {
            const lat = parseFloat(stop.ycord);
            const lng = parseFloat(stop.xcord);
            const position = new kakao.maps.LatLng(lat, lng);
            const {marker, infowindow} = createBusStopMarker(position, stop.bstpNm);

            marker.setMap(map);
            stopMarkers.push({marker, infowindow});
            linePath.push(position);

            // 경계 계산
            latMin = Math.min(latMin, lat);
            latMax = Math.max(latMax, lat);
            lngMin = Math.min(lngMin, lng);
            lngMax = Math.max(lngMax, lng);
        });

        // 노선 라인 그리기
        if (linePath.length > 0) {
            routeLine = new kakao.maps.Polyline({
                path: linePath,
                strokeWeight: 3,
                strokeColor: '#3B89E6',
                strokeOpacity: 0.7,
                strokeStyle: 'solid'
            });

            routeLine.setMap(map);

            // 지도 영역 설정
            const bounds = new kakao.maps.LatLngBounds();
            bounds.extend(new kakao.maps.LatLng(latMin, lngMin));
            bounds.extend(new kakao.maps.LatLng(latMax, lngMax));
            map.setBounds(bounds);
        }

    } catch (error) {
        console.error('노선 표시 실패:', error);
        alert('노선 정보를 표시하는데 실패했습니다.');
    }
}

// 정류장 및 노선 초기화 함수
function resetBusStops() {
    // 정류장 마커 제거
    if (stopMarkers && stopMarkers.length > 0) {
        stopMarkers.forEach(marker => {
            if (marker.marker) marker.marker.setMap(null);
            if (marker.infowindow) marker.infowindow.setMap(null);
        });
        stopMarkers = [];
    }

    // 노선 라인 제거
    if (routeLine) {
        routeLine.setMap(null);
        routeLine = null;
    }
}

// 버스 위치 업데이트
async function updateBusLocation(obuId) {
    try {
        const routeNumber = document.querySelector('input[name="routeNumber"]:checked').value;
        const routeId = ROUTE_ID_MAP[routeNumber];

        const busInfo = await getBusLocation(obuId, routeId);

        // 기존 마커 제거
        busMarkers.forEach(marker => marker.setMap(null));
        busMarkers.clear();

        // 새 마커 생성
        const position = new kakao.maps.LatLng(busInfo.ycord, busInfo.xcord);
        const marker = createBusMarker(position, busInfo.obuId);
        marker.setMap(map);
        busMarkers.set(busInfo.obuId, marker);

    } catch (error) {
        console.error('버스 위치 업데이트 실패:', error);
        stopMonitoring();
        alert('버스 위치 정보를 가져오는데 실패했습니다.');
    }
}

// 모니터링 시작
function startMonitoring() {
    const obuId = document.getElementById('obuId').value;
    const routeRadio = document.querySelector('input[name="routeNumber"]:checked');

    if (!obuId || !routeRadio) {
        alert('버스 ID와 노선을 선택해주세요.');
        return;
    }

    stopMonitoring();
    updateBusLocation(obuId);
    updateInterval = setInterval(() => updateBusLocation(obuId), 3000);
}

// 모니터링 중지
function stopMonitoring() {
    if (updateInterval) {
        clearInterval(updateInterval);
        updateInterval = null;
    }
}



// 운행 시작
async function startOperation() {
    const obuId = document.getElementById('obuId').value;
    const routeRadio = document.querySelector('input[name="routeNumber"]:checked');

    if (!obuId || !routeRadio) {
        alert('버스 ID와 노선을 선택해주세요.');
        return;
    }

    try {
        const response = await fetch(`/api/simulator/operation/start?obuId=${obuId}`, {
            method: 'POST'
        });

        if (!response.ok) {
            throw new Error('운행 시작 처리에 실패했습니다.');
        }

        alert('운행이 시작되었습니다.');
        startMonitoring();  // 실시간 모니터링 시작
    } catch (error) {
        console.error('운행 시작 실패:', error);
        alert(error.message);
    }
}

// 전체 초기화 (기존 stopRouteOperation 함수명 변경)
async function stopRouteOperation() {
    const routeRadio = document.querySelector('input[name="routeNumber"]:checked');

    if (!routeRadio) {
        alert('노선을 선택해주세요.');
        return;
    }

    const routeId = ROUTE_ID_MAP[routeRadio.value];

    try {
        const response = await fetch(`/api/simulator/operation/stop-route?routeId=${routeId}`, {
            method: 'POST'
        });

        if (!response.ok) {
            throw new Error('운행 종료 처리에 실패했습니다.');
        }

        // 모니터링 중지 및 화면 초기화
        stopMonitoring();
        resetBusStops();

        // 버스 마커 제거
        if (busMarkers) {
            busMarkers.forEach(marker => marker.setMap(null));
            busMarkers.clear();
        }

        // 입력 필드 초기화
        document.getElementById('obuId').value = '';
        routeRadio.checked = false;

        alert('노선 운행이 종료되었습니다.');
    } catch (error) {
        console.error('운행 종료 실패:', error);
        alert(error.message);
    }
}



// 이벤트 리스너 설정
document.addEventListener('DOMContentLoaded', () => {
    initMap().then(() => {
        document.getElementById('startOperation').addEventListener('click', startOperation);
        document.getElementById('stopOperation').addEventListener('click', stopRouteOperation);

        // 노선 선택 이벤트
        document.querySelectorAll('input[name="routeNumber"]').forEach(radio => {
            radio.addEventListener('change', () => displayBusStops(radio.value));
        });
    });
});