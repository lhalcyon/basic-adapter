# basic-adapter



 一个通用的RecyclerView的Adapter,主要支持:

* 添加头,尾布局
* 预加载更多
* 支持单、多选条目(同ListView的ChoiceMode)
* 支持空数据布局
* Builder mode 链式初始化配置
* 添加条目点击事件(普通条目,不包括头尾)



### Download

Grab via Maven:

```xml
<dependency>
  <groupId>com.lhalcyon</groupId>
  <artifactId>basic-adapter</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

or Gradle:

```groovy
compile 'com.lhalcyon:basic-adapter:1.0.1' 
```



### 用法:

初始化适配器:

```java
BasicParams p = new BasicController.Builder()
					.layoutRes(R.layout.item)//普通条目布局 ,必要属性
					.build();
mRecyclerView.setAdapter(mAdapter = new MyAdapter(p,list));
```

 可选的配置如下:

```java
BasicParams p = new BasicController.Builder()
               .layoutRes(R.layout.item)
               .header(header)
               .header(header2)
               .footer(footer)
               .empty(empty)
               .loaded(loaded)//没有更多数据  布局
               .loading(loading)//上拉加载 布局
               .onLoadMore(new OnLoadMoreListener() {//加载更多监听(注意线程)
                    @Override
                    public void onLoad() {
                       //加载更多
                    }
                })
               .build();        
```

![](https://github.com/lhalcyon/basic-adapter/raw/master/art/recycler-regular.gif)



单/多选 条目配置

```java
BasicParams params = new BasicController.Builder()
                .checkId(R.id.checkbox)//checkable 控件 id, 目前只支持条目id唯一
                .choiceMode(BasicController.CHOICE_MODE_MULTIPLE)//选择模式 单选,多,无
                .layoutRes(R.layout.item_check)
                .build();
```

Then  override method  `isItemChecked(T t,int position)` and set `OnItemClickListener` which is necessary

```java
mRecyclerView.setAdapter(mAdapter = new CheckAdapter(params,mManList){
            @Override
            public boolean isItemChecked(Man man, int position) {
                return man.isSingle;//必复写的方法,用来初始化条目选择状态,如果适配器为单选,此方法却有多个条目返回true,则只有最后一个返回true的条目是选中的.
            }
        });
mAdapter.setOnItemClickListener(mRecyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder vh, int position) {
                //单击条目事件,必须设置,内容可空
            }
        });
```

单选模式:

![single choice](https://github.com/lhalcyon/basic-adapter/raw/master/art/recycler-check-single.gif)



多选模式:

![multiple choice](https://github.com/lhalcyon/basic-adapter/raw/master/art/recycler-check-multiple.gif)

 gif效果比较差 :(  , 在真机以及模拟器上效果不错.



# License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```