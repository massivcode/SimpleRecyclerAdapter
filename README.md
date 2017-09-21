[![](https://jitpack.io/v/prChoe/SimpleRecyclerAdapter.svg)](https://jitpack.io/#prChoe/SimpleRecyclerAdapter)

# 1. What is SimpleRecyclerAdapter?

This Library is helpful to escape from boilerplate code.

이 라이브러리는 상투적이고 반복되는 코드로부터 탈출하는데 도움을 줍니다.

When we develop android application, In most cases we face with multiple different layout in one list layout.

안드로이드 앱 개발을 할 때, 대부분의 경우 하나의 리스트 레이아웃에 여러가지 상이한 레이아웃이 포함되어 있는 상황에 직면합니다.

Otherwise we coding many codes for RecyclerView.Adapter.

그렇지 않은 경우에도 어댑터 코드를 많이 작성해야 합니다.

We should define dataSource, viewTypes and data manipulation codes...

데이터 소스와 뷰 타입 그리고 데이터 관련 코드를 작성하고...

As a result, our fingers become sick and exhausted by boilerplate code.

그 결과로 손가락은 아파지고 상투적인 코드 때문에 지칩니다.

When you use this library, adapter will be delegate bind data to view holders.

이 라이브러리를 사용한다면 어댑터는 뷰 홀더에게 데이터 바인딩을 위임합니다.

ViewHolder will be bind data to views and handle item click events.

뷰 홀더는 데이터를 뷰에 바인딩하고 아이템 클릭 이벤트를 핸들링합니다. 

Write once, use everywhere.

한 번 작성하고, 모든 곳에서 사용하세요.

## 2. Preview
![Screenshot](https://github.com/prChoe/SimpleRecyclerAdapter/blob/master/Screenshot-SimpleRecyclerAdapter.png) 

## 3. Setup

### 1. Gradle

```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```gradle
	dependencies {
	        compile 'com.github.prChoe:SimpleRecyclerAdapter:0.1'
	}
```

### 2. Maven

```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```maven
	<dependency>
	    <groupId>com.github.prChoe</groupId>
	    <artifactId>SimpleRecyclerAdapter</artifactId>
	    <version>0.1</version>
	</dependency>

```

## 4. How to use

### Step 1. Define model and item layout

Define model class that implements Item interface

Item 인터페이스를 구현하는 모델 클래스와 아이템 레이아웃을 작성하세요.

```java
public class Contact implements Item {
    private String name, phoneNumber;

    ...
    
    @Override
    public int getViewType() {
        return ViewTypes.CONTACT;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.item_contact;
    }

    ...
}
```

### Step 2. Define ViewHolders

Define ViewHolder class that extends BaseViewHolder

BaseViewHolder 를 상속하는 뷰 홀더 클래스를 작성하세요.

```java
public class ContactViewHolder extends BaseViewHolder {
    private TextView mNameTextView, mPhoneNumberTextView;

    public ContactViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView, viewType, clickEventBus);

        mNameTextView = (TextView) itemView.findViewById(R.id.name_tv);
        mPhoneNumberTextView = (TextView) itemView.findViewById(R.id.phoneNumber_tv);
        ImageView profileImageView = (ImageView) itemView.findViewById(R.id.profile_iv);

        
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindItem(Item item, int position) {
        Contact contact = (Contact) item;

        mNameTextView.setText(contact.getName());
        mPhoneNumberTextView.setText(contact.getPhoneNumber());
    }
}
```

```java
public class MemoViewHolder extends BaseViewHolder {
    private TextView mTitleTextView, mContentsTextView;

    public MemoViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        super(itemView, viewType, clickEventBus);

        mTitleTextView = (TextView) itemView.findViewById(R.id.title_tv);
        mContentsTextView = (TextView) itemView.findViewById(R.id.contents_tv);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked(view, getAdapterPosition());
            }
        });
    }

    @Override
    public void onBindItem(Item item, int position) {
        Memo memo = (Memo) item;

        mTitleTextView.setText(memo.getTitle());
        mContentsTextView.setText(memo.getContents());
    }
}
```

### Step 3. Define Adapter

Define Adapter class that extends SimpleRecyclerAdapter

SimpleRecyclerAdapter 를 상속하는 어댑터 클래스를 작성하세요.

```java
public class TestAdapter extends SimpleRecyclerAdapter<Item> {

    public TestAdapter(List<Item> items) {
        super(items);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View itemView, int viewType, ClickEventBus clickEventBus) {
        BaseViewHolder viewHolder = null;

        switch (viewType) {
            case ViewTypes.ARTICLE:
                viewHolder = new ArticleViewHolder(itemView, viewType, clickEventBus);
                break;
            case ViewTypes.CONTACT:
                viewHolder = new ContactViewHolder(itemView, viewType, clickEventBus);
                break;
            case ViewTypes.MEMO:
                viewHolder = new MemoViewHolder(itemView, viewType, clickEventBus);
                break;
        }

        return viewHolder;
    }
}
```

### Step 4. Set Adapter into RecyclerView and set item click listener for viewType

RecyclerView 에 어댑터를 설정하고, 특정 뷰 타입에 대한 아이템 클릭 리스너를 설정하세요.

```java
public class TestActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    ...

    public void setup(List<Item> items) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        TestAdapter testAdapter = new TestAdapter(items);

        testAdapter.setOnItemClickListener(ViewTypes.CONTACT, new ItemClickListener<Item>() {
            @Override
            public void onItemClick(View view, int position, Item item) {
                int viewId = view.getId();

                if (viewId == R.id.profile_iv) {
                    // Profile ImageView Clicked
                    System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
                } else {
                    // ItemView Clicked
                    System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
                }
            }
        });

        testAdapter.setOnItemClickListener(ViewTypes.ARTICLE, new ItemClickListener<Item>() {
            @Override
            public void onItemClick(View view, int position, Item item) {
                System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
            }
        });

        testAdapter.setOnItemClickListener(ViewTypes.MEMO, new ItemClickListener<Item>() {
            @Override
            public void onItemClick(View view, int position, Item item) {
                System.out.println("ClickedView : " + view + ", position: " + position + ", item: " + item);
            }
        });

        mRecyclerView.setAdapter(testAdapter);
    }

}
```

## 5. API (@Adapter)

```java
    
    /**
     *
     * @param viewType The ViewType to specify view holder
     * @param listener The ItemClickListener to handle specified view holder's click event
     */
    public void setOnItemClickListener(int viewType, ItemClickListener<ITEM> listener)

    /**
     * Get the data item associated with the specified position in the data set.
     * @param position Position of the item whose data we want within the adapter's data set.
     * @return The data at the specified position.
     */
    public ITEM getItem(int position)
    
    /**
     * The Method to replace data source
     * @param items The new objects to represent in the RecyclerView.
     */
    public void refreshItems(List<ITEM> items)

    /**
     * The Method to add item object into data source
     * @param item The object that will be added into data source
     */
    public void addItem(ITEM item)
    
    /**
     * The Method to add item object into data source's specified position
     * @param item The object that will be added into data source
     * @param position The position to which data object will be added into data source
     */
    public void addItem(ITEM item, int position)

    /**
     * The Method to add item objects into data source
     * @param items The objects that will be added into data source
     */
    public void addItems(List<ITEM> items)

    /**
     * The Method to add item objects into data source's specified position
     * @param items The objects that will be added into data source
     * @param position The position to which data objects will be added into data source
     */
    public void addItems(List<ITEM> items, int position)

    /**
     * The Method to remove item that specified position
     * @param position The position to which will be removed from data source
     */
    public void removeItem(int position)

    /**
     * The Method to update item that specified position
     * @param item The Item that replace old item at position
     * @param position The position to witch will be update from data source
     */
    public void updateItem(ITEM item, int position)
```


