package sosconnector.Filter;

import java.util.LinkedList;

/**
 * Created by zil on 2016/4/21.
 */
public class Filter {

    private LinkedList<String> list;

    public Filter(LinkedList<String> set){
        this.list = set;
    }

    public LinkedList<String> compare( LinkedList<String> list){
        if(list.size() == this.list.size())
            return this.list;
        return clean(list);
    }

    public LinkedList<String> unique(){

        return null;
    }

    private LinkedList<String> clean(LinkedList<String> list){
        for( String xml: this.list) {
            list.remove(xml);
        }
        addAll( list );
        return list;
    }

    private void addAll(LinkedList<String> list){
        if( !list.isEmpty() )
            this.list.addAll(list);
    }
}
