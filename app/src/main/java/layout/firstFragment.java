package layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.howtodraw.draw.BaseFragment;
import com.learn.howtodraw.draw.LinkBooks;
import com.learn.howtodraw.draw.PageActivity;
import com.learn.howtodraw.draw.R;

import static com.learn.howtodraw.draw.Constants.SKU_BOOK_NAME_ARRAY;
import static com.learn.howtodraw.draw.Constants.mPurchasedBooksArray;

//A placeholder fragment containing a simple view.
public class firstFragment extends BaseFragment {
    //The fragment argument representing the section number for this fragment.
    private static final String ARG_SECTION_NUMBER = "section_number";
    public int willyCounter;


    public firstFragment() {
    }

    // Returns a new instance of this fragment for the given section number
    public static firstFragment newInstance(int sectionNumber) {
        firstFragment fragment = new firstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        LinkBooks.linkBook(R.id.layout_1, R.array.book1Build, rootView, false, getActivity());





/*        final VideoView vidView = (VideoView) rootView.findViewById(R.id.myVideoA);
        String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
        Uri vidUri = Uri.parse(vidAddress);
        vidView.setVideoURI(vidUri);

        final View vV =  rootView.findViewById(R.id.video);

        vV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("log taht","as");
                vidView.start();
            }
        });*/

        TextView tv = (TextView) rootView.findViewById(R.id.removeBook5);
        assert tv != null;
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[4]);
                mPurchasedBooksArray[4] = false;

            }
        });


        final ImageView iV = (ImageView) rootView.findViewById(R.id.willy);
        final TextView tV = (TextView) rootView.findViewById(R.id.will_header);
        final TextView tV2 = (TextView) rootView.findViewById(R.id.will_text);
        final TextView tV3 = (TextView) rootView.findViewById(R.id.removeBook5);

        iV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                willyCounter ++;
                if (willyCounter == 5)
                {

                    iV.setImageResource(R.drawable.mario);
                    tV.setText("Aidan Sliney is a legend");
                    tV2.setText("What a legend");
                    tV3.setText("Unpurchase Book 5");

                    Log.d("Clicked", "Willy");
                }
            }
        });

        Log.d("subscribed?", "" + isSubscribed());

        View homeCard1 = rootView.findViewById(R.id.home_card_1);
        homeCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);

                intent.putExtra("tutorialId", "b03t02");
                intent.putExtra("bookHelp", R.array.b03t02Help);

                intent.putExtra("bookName", "book03");
                startActivity(intent);
            }
        });

        View homeCard2 = rootView.findViewById(com.learn.howtodraw.draw.R.id.home_card_2);
        homeCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);

                intent.putExtra("bookHelp", R.array.b02t03Help);
                intent.putExtra("tutorialId", "b02t03");
                intent.putExtra("bookName", "book02");
                startActivity(intent);
            }
        });

        View artist = rootView.findViewById(R.id.layout_4);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return rootView;
    }



}