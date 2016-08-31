package layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.learn.howtodraw.draw.BaseFragment;
import com.learn.howtodraw.draw.CustomGrid;
import com.learn.howtodraw.draw.ExpandableGridView;
import com.learn.howtodraw.draw.MyDialogFragment;
import com.learn.howtodraw.draw.PageActivity;
import com.learn.howtodraw.draw.R;

import static com.learn.howtodraw.draw.Constants.*;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class ThirdFragment extends BaseFragment {


    boolean hasPurchased;
    int bookThumb;
    int bookName;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BrowseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(int param1, int param2, int param3, int param4) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putInt("cardText1Id", param1);
        args.putInt("bookPageIds", param2);
        args.putInt("cardImageId", param3);
        args.putInt("booksBooks", param4); //the book the page belongs to




        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //send content in the grid
        final String[] cardText1 = getResources().getStringArray(getArguments().getInt("cardText1Id"));
        final String[] bookPageIds = getResources().getStringArray(getArguments().getInt("bookPageIds"));
        final String[] booksBook = getResources().getStringArray(getArguments().getInt("booksBooks"));
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getArguments().getInt("cardImageId"));
        final int[] cardImage = new int[cardImageDrawables.length()];
        final int[] tickIcon = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++) {
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);



            if (booksBook[i].equals("book1"))
            {
                hasPurchased = mPurchasedBook1;
            }
            if (booksBook[i].equals("book2"))
            {
                hasPurchased = mPurchasedBook2;
            }
            if (booksBook[i].equals("book3"))
            {
                hasPurchased = mPurchasedBook3;
            }
            if (booksBook[i].equals("book4"))
            {
                hasPurchased = mPurchasedBook4;
            }
            if (booksBook[i].equals("book5"))
            {
                hasPurchased = mPurchasedBook5;
            }

            if(hasPurchased)
                tickIcon[i] = R.string.fa_check;
            else
                tickIcon[i] = R.string.fa_lock;

        }
        final CustomGrid adapter = new CustomGrid(getActivity(), cardText1, cardImage, bookPageIds, tickIcon);
        //set the grid
        ExpandableGridView gridView = (ExpandableGridView) view.findViewById(R.id.gridthird);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        gridView.setFocusable(false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pageId = (String) adapter.getItem(position);


                if (booksBook[position].equals("book1"))
                {
                    hasPurchased = mPurchasedBook1;
                    bookThumb = R.drawable.book1cover;
                    bookName = R.string.book1;
                }
                if (booksBook[position].equals("book2"))
                {
                    hasPurchased = mPurchasedBook2;
                    bookThumb = R.drawable.book2cover;
                    bookName = R.string.book2;
                }
                if (booksBook[position].equals("book3"))
                {
                    hasPurchased = mPurchasedBook3;
                    bookThumb = R.drawable.book3cover;
                    bookName = R.string.book3;
                }
                if (booksBook[position].equals("book4"))
                {
                    hasPurchased = mPurchasedBook4;
                    bookThumb = R.drawable.book4cover;
                    bookName = R.string.book4;
                }
                if (booksBook[position].equals("book5"))
                {
                    hasPurchased = mPurchasedBook5;
                    bookThumb = R.drawable.book5cover;
                    bookName = R.string.book5;
                }

                if(hasPurchased)
                {
                    int slidesID = getActivity().getResources().getIdentifier(pageId + "Slides", "array", getContext().getPackageName());
                    Intent intent = new Intent(getActivity(),PageActivity.class);
                    intent.putExtra("bookSlides", slidesID);
                    startActivity(intent);
                }
                else
                {
                    Bundle bundle = new Bundle();
                    bundle.putInt("bookThumb", bookThumb);
                    bundle.putInt("bookName", bookName);

                    FragmentManager fm = getFragmentManager();
                    MyDialogFragment dialogFragment = new MyDialogFragment();
                    dialogFragment.setArguments(bundle);
                    dialogFragment.show(fm, getString(R.string.menu_subscribe));
                }


            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener2 = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
