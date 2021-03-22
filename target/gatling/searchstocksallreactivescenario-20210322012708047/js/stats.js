var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "68",
        "ok": "68",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "327",
        "ok": "327",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2515",
        "ok": "2515",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "804",
        "ok": "804",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "381",
        "ok": "381",
        "ko": "-"
    },
    "percentiles1": {
        "total": "724",
        "ok": "724",
        "ko": "-"
    },
    "percentiles2": {
        "total": "939",
        "ok": "939",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1451",
        "ok": "1451",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2128",
        "ok": "2128",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 36,
    "percentage": 53
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 25,
    "percentage": 37
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 7,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.236",
        "ok": "1.236",
        "ko": "-"
    }
},
contents: {
"req_get-all-stocks-86ac3": {
        type: "REQUEST",
        name: "Get all stocks",
path: "Get all stocks",
pathFormatted: "req_get-all-stocks-86ac3",
stats: {
    "name": "Get all stocks",
    "numberOfRequests": {
        "total": "68",
        "ok": "68",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "327",
        "ok": "327",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2515",
        "ok": "2515",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "804",
        "ok": "804",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "381",
        "ok": "381",
        "ko": "-"
    },
    "percentiles1": {
        "total": "724",
        "ok": "724",
        "ko": "-"
    },
    "percentiles2": {
        "total": "939",
        "ok": "939",
        "ko": "-"
    },
    "percentiles3": {
        "total": "1451",
        "ok": "1451",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2128",
        "ok": "2128",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 36,
    "percentage": 53
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 25,
    "percentage": 37
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 7,
    "percentage": 10
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.236",
        "ok": "1.236",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
