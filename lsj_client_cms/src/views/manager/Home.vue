<template>
  <div>
    <div class="card" style="padding: 15px">
      你好，{{ user?.name }}
    </div>

    <div style="display: flex; grid-gap: 10px; margin: 10px 0">
      <div style="flex: 1; height: 500px" id="bar" class="card"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

const barOption = {
  title: {
    text: '用户库存流水',
    subtext: '柱状图',
    left: 'center'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    left: 'left'
  },
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [],
      type: 'bar',
      smooth: true,
      itemStyle: {
        normal: {
          color: function(params) {
            let colorList = ['#ffaa2e','#32C5E9','#fa4c4c','#08b448','#FFDB5C','#ff9f7f','#fb7293','#E062AE','#E690D1','#e7bcf3']
            return colorList[params.dataIndex];
          }
        }
      },
    }
  ]
}


export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  mounted() {
    let barDom = document.getElementById('bar');
    let barChart = echarts.init(barDom);

    this.$request.get('/orders/selectBar').then(res => {
      barOption.xAxis.data = res.data?.map(v => v.name) || []
      barOption.series[0].data = res.data?.map(v => v.value) || []
      barChart.setOption(barOption)
    })
  }
}
</script>
