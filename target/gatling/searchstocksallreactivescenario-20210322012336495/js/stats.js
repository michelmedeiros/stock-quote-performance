var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "78",
        "ok": "78",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "652",
        "ok": "652",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "7436",
        "ok": "7436",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3244",
        "ok": "3244",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1550",
        "ok": "1550",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3073",
        "ok": "3073",
        "ko": "-"
    },
    "percentiles2": {
        "total": "4257",
        "ok": "4257",
        "ko": "-"
    },
    "percentiles3": {
        "total": "6216",
        "ok": "6216",
        "ko": "-"
    },
    "percentiles4": {
        "total": "6702",
        "ok": "6702",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 2,
    "percentage": 3
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 4,
    "percentage": 5
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 72,
    "percentage": 92
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.444",
        "ok": "1.444",
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
        "total": "78",
        "ok": "78",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "652",
        "ok": "652",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "7436",
        "ok": "7436",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "3244",
        "ok": "3244",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1550",
        "ok": "1550",
        "ko": "-"
    },
    "percentiles1": {
        "total": "3073",
        "ok": "3073",
        "ko": "-"
    },
    "percentiles2": {
        "total": "4257",
        "ok": "4257",
        "ko": "-"
    },
    "percentiles3": {
        "total": "6216",
        "ok": "6216",
        "ko": "-"
    },
    "percentiles4": {
        "total": "6702",
        "ok": "6702",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 2,
    "percentage": 3
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 4,
    "percentage": 5
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 72,
    "percentage": 92
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.444",
        "ok": "1.444",
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
