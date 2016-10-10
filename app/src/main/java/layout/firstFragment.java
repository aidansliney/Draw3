package layout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.howtodraw.draw.BaseFragment;
import com.learn.howtodraw.draw.LinkBooks;
import com.learn.howtodraw.draw.MainActivity;
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
        LinkBooks.linkBook(R.id.layout_1, R.array.book2Build, rootView, false, getActivity());

        final TextView rb1 = (TextView) rootView.findViewById(R.id.removeBook1);
        final TextView rb2 = (TextView) rootView.findViewById(R.id.removeBook2);
        final TextView rb3 = (TextView) rootView.findViewById(R.id.removeBook3);
        final TextView rb4 = (TextView) rootView.findViewById(R.id.removeBook4);
        final TextView rb5 = (TextView) rootView.findViewById(R.id.removeBook5);
        final TextView rb6 = (TextView) rootView.findViewById(R.id.removeBook6);
        final TextView rb7 = (TextView) rootView.findViewById(R.id.removeBook7);
        rb1.setVisibility(View.GONE);
        rb2.setVisibility(View.GONE);
        rb3.setVisibility(View.GONE);
        rb4.setVisibility(View.GONE);
        rb5.setVisibility(View.GONE);
        rb6.setVisibility(View.GONE);
        rb7.setVisibility(View.GONE);

        rb1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[1]);
                mPurchasedBooksArray[1] = false;
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[2]);
                mPurchasedBooksArray[2] = false;
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[3]);
                mPurchasedBooksArray[3] = false;
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[4]);
                mPurchasedBooksArray[4] = false;
            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[5]);
                mPurchasedBooksArray[5] = false;
            }
        });

        rb6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[6]);
                mPurchasedBooksArray[6] = false;
            }
        });

        rb7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getMainActivity().consumeBook(SKU_BOOK_NAME_ARRAY[7]);
                mPurchasedBooksArray[7] = false;
            }
        });

        final ImageView iV = (ImageView) rootView.findViewById(R.id.willy);
        final TextView tV = (TextView) rootView.findViewById(R.id.will_header);
        final TextView tV2 = (TextView) rootView.findViewById(R.id.will_text);

        iV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                willyCounter ++;
                if (willyCounter == 5)
                {
                    iV.setImageResource(R.drawable.mario);
                    tV.setText("Aidan Sliney is a legend");
                    tV2.setText("What a legend");
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    rb3.setVisibility(View.VISIBLE);
                    rb4.setVisibility(View.VISIBLE);
                    rb5.setVisibility(View.VISIBLE);
                    rb6.setVisibility(View.VISIBLE);
                    rb7.setVisibility(View.VISIBLE);

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
                intent.putExtra("tutorialId", "b02t03");
                intent.putExtra("bookHelp", R.array.b02t03Help);
                intent.putExtra("bookName", "book02");
                startActivity(intent);
            }
        });

        View helpCard3 = rootView.findViewById(com.learn.howtodraw.draw.R.id.layout_0);
        helpCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PageActivity.class);
                intent.putExtra("bookHelp", R.array.b00t01Help);
                intent.putExtra("tutorialId", "b00t01");
                intent.putExtra("bookName", "book00");
                startActivity(intent);
            }
        });


        View artist = rootView.findViewById(R.id.layout_4);
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        View feedback = rootView.findViewById(R.id.feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        return rootView;
    }


    public void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"aidansliney@gmail.com"};
        String[] CC = {"wsliney@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Draw Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Please write your feedback here. The more info the better");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }
}