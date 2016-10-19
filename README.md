# basic-adapter



[中文版](https://github.com/lhalcyon/basic-adapter/blob/master/README-CN.md)

A common tool adapter for Android RecyclerView.basic functions of current version as below:

* add header & footer
* load more
* copy function `choice mode` in ListView
* set empty view
* easy 2 config adapter via builder mode
* add item click



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



### Usage

Initialize adapter :

```java
BasicParams p = new BasicController.Builder()
					.layoutRes(R.layout.item)//normal item layout resource ,necessary
					.build();
mRecyclerView.setAdapter(mAdapter = new MyAdapter(p,list));
```

Optional configuration as below:

```java
BasicParams p = new BasicController.Builder()
               .layoutRes(R.layout.item)
               .header(header)
               .header(header2)
               .footer(footer)
               .empty(empty)
               .loaded(loaded)//loaded view
               .loading(loading)//loading view
               .onLoadMore(new OnLoadMoreListener() {//load more listener
                    @Override
                    public void onLoad() {
                       //do loading thing
                    }
                })
               .build();        
```

![](https://github.com/lhalcyon/basic-adapter/raw/master/art/recycler-regular.gif)



Checkable item config:

```java
BasicParams params = new BasicController.Builder()
                .checkId(R.id.checkbox)
                .choiceMode(BasicController.CHOICE_MODE_MULTIPLE)
                .layoutRes(R.layout.item_check)
                .build();
```

Then  override method  `isItemChecked(T t,int position)` and set `OnItemClickListener` which is necessary

```java
mRecyclerView.setAdapter(mAdapter = new CheckAdapter(params,mManList){
            @Override
            public boolean isItemChecked(Man man, int position) {
                return man.isSingle;//check state associated with bean
            }
        });
mAdapter.setOnItemClickListener(mRecyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder vh, int position) {
                //do item click thing
            }
        });
```

single choice mode:

![single choice](https://github.com/lhalcyon/basic-adapter/raw/master/art/recycler-check-single.gif)



multiple choice mode:

![multiple choice](https://github.com/lhalcyon/basic-adapter/raw/master/art/recycler-check-multiple.gif)

Gif is under poor quality :( ,but the lib  works well shown on real device and virtual device



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