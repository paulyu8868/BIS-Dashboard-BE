# 군산시 버스정보시스템(BIS) - Backend

## 프로젝트 개요
군산시 대중교통 이용 활성화를 통한 탄소중립 기여를 목표로 하는 버스정보시스템 백엔드 서버입니다.
<img width="844" height="412" alt="image" src="https://github.com/user-attachments/assets/5d5bb3fe-557c-491a-8519-6731bbb33b03" />


## 주요 기능
- 실시간 버스 위치 정보 제공
- 노선별 정류장 및 경로 데이터 API
- 버스 운행 시뮬레이터
- 탄소 배출량 계산 지원 API

## 기술 스택
- **Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL (AWS RDS)
- **API Testing**: Postman
- **Build Tool**: Maven/Gradle


## 주요 API 엔드포인트

### 노선 관련 API
- `GET /api/route/top10` - 상위 10개 노선 조회
- `GET /api/route/busstops/{routeId}` - 특정 노선의 정류장 목록 조회
- `GET /api/route/vertices/{routeId}` - 노선 경로 좌표 조회
- `GET /api/route/{routeId}/buses/stops` - 노선의 실시간 버스 위치 조회

### 시뮬레이터 API
- `GET /api/simulator/buses/{routeId}` - 시뮬레이션용 버스 목록 조회
- `POST /api/simulator/start` - 버스 시뮬레이션 시작
- `GET /api/simulator/buses/locations/{routeId}` - 시뮬레이션 버스 위치 조회
- `POST /api/simulator/stop` - 시뮬레이션 중지

## 데이터베이스 구조

### 주요 테이블
- **M_OP_ROUTE_POINT**: 노선별 경유 지점 정보
- **M_TP_LINK**: 노드 간 링크 정보
- **M_TP_LINK_VERTEX**: 링크의 상세 좌표 정보
- **M_TP_BSTP**: 정류장 정보
- **C_TC_BUS_RUNG**: 실시간 버스 운행 정보
- **M_OP_BUS**: 버스 차량 정보
- **M_OP_OBU**: 버스 단말기 정보

## 성능 최적화

### 데이터 조회 최적화
기존에 개별 SELECT 문으로 인해 10초 이상 걸리던 조회를 JOIN 쿼리로 최적화하여 성능을 크게 개선했습니다.

#### 정류장 조회 쿼리
```sql
SELECT DISTINCT rp.POINT_SQNO, bs.BSTP_NM, bs.XCORD, bs.YCORD
FROM M_TP_BSTP bs, M_OP_ROUTE_POINT rp
WHERE rp.ROUTE_ID = :routeId
  AND rp.POINT_ID = bs.BSTP_ID
ORDER BY rp.POINT_SQNO;
```

#### 버텍스 조회 쿼리
```sql
SELECT DISTINCT rp.POINT_SQNO, v.SQNO, v.XCORD, v.YCORD 
FROM M_TP_LINK l, M_TP_LINK_VERTEX v, M_OP_ROUTE_POINT rp, M_TP_NODE n
WHERE rp.ROUTE_ID = :routeId
  AND rp.POINT_ID = n.NODE_ID
  AND l.STRT_NODE_ID = rp.BEF_POINT_ID
  AND l.END_NODE_ID = rp.POINT_ID
  AND l.LINK_ID = v.LINK_ID
ORDER BY rp.POINT_SQNO, v.SQNO;
```

## 시뮬레이터 구현

### 버스 위치 갱신 로직
5초 주기로 실시간 버스 현황 정보를 갱신하며, 다음 9개 필드를 업데이트합니다:
1. `XCORD`, `YCORD` - 현재 위치 좌표
2. `MMNT_SPD` - 순간 속도 (km/h)
3. `PASG_POINT_ID`, `PASG_POINT_SQNO` - 통과 지점 정보
4. `POINT_PASG_DT` - 통과 시각
5. `ARRVL_PLNND_POINT_ID`, `ARRVL_PLNND_POINT_SQNO` - 도착 예정 지점
6. `BUS_LOC_DIV` - 버스 위치 구분 (01: 정주기, 02: 진입, 03: 진출)

### 상태 판단 알고리즘
- **진입(02)**: 정류장까지 거리 < `ENT_JDG_DIST`
- **진출(03)**: 정류장으로부터 거리 > `EXT_JDG_DIST`
- **정주기(01)**: 위 조건에 해당하지 않는 경우


## 향후 개선 계획

### 성능 최적화
- 개별 노선 조회 API 구현으로 초기 로딩 시간 단축
- 캐싱 전략 도입으로 반복 조회 성능 개선
- 데이터베이스 인덱스 최적화

### 기능 개선
- WebSocket을 활용한 실시간 위치 업데이트
- 버스 도착 예정 시간 예측 알고리즘 고도화
- 탄소 배출량 계산 정확도 향상

## 문제 해결

### 데이터 로딩 속도 문제
- **문제**: 초기 구현 시 노선 데이터 로딩에 10초 이상 소요
- **원인**: 다수의 개별 SELECT 쿼리 실행
- **해결**: JOIN을 활용한 쿼리 최적화로 로딩 시간 대폭 단축

### 노선 경로 표시 정확도
- **문제**: 버텍스 순서가 맞지 않아 경로가 꼬이는 현상
- **해결**: POINT_SQNO와 VERTEX SQNO를 활용한 정렬로 올바른 경로 표시
