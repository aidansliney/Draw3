package layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.learn.howtodraw.draw.BaseFragment;
import com.learn.howtodraw.draw.CustomGridPagesFragment2;
import com.learn.howtodraw.draw.ExpandableGridView;
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


public class BrowseFragment extends BaseFragment {


    boolean hasPurchased;

    public BrowseFragment() {
        // Required empty public constructor
    }

    public static BrowseFragment newInstance(int param1, int param2, int param3, int param4, int param5) {
        BrowseFragment fragment = new BrowseFragment();
        Bundle args = new Bundle();
        args.putInt("cardText1Id", param1);
        args.putInt("bookPageIds", param2);
        args.putInt("cardImageId", param3);
        args.putInt("booksBooks", param4); //the book the page belongs to
        args.putInt("bookLevel", param5); //the book the page belongs to
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_browse, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //send content in the grid. The data is sent to the third fragment from ...Main Activity
        final String[] cardText1 = getResources().getStringArray(getArguments().getInt("cardText1Id"));
        final String[] bookPageIds = getResources().getStringArray(getArguments().getInt("bookPageIds"));
        final String[] bookNames = getResources().getStringArray(getArguments().getInt("booksBooks"));
        final String[] bookLevels = getResources().getStringArray(getArguments().getInt("bookLevel"));
        final TypedArray cardImageDrawables = getResources().obtainTypedArray(getArguments().getInt("cardImageId"));

        final int[] cardImage = new int[cardImageDrawables.length()];
        final int[] tickIcon = new int[cardImageDrawables.length()];
        for (int i = 0; i < cardImageDrawables.length(); i++) {
            cardImage[i] = cardImageDrawables.getResourceId(i, 0);

            int counter = 0;
            while(counter < mPurchasedBooksArray.length)
            {

                if (bookNames[i].equals(SKU_BOOK_NAME_ARRAY[counter]))
                {
                    hasPurchased =  mPurchasedBooksArray[counter];
                }
                counter++;
            }

            if(hasPurchased || mSubscribed)
                tickIcon[i] = R.string.fa_check;
            else
                tickIcon[i] = R.string.fa_lock;

        }
        final CustomGridPagesFragment2 adapter = new CustomGridPagesFragment2(getActivity(), cardText1, cardImage, bookPageIds, bookNames);
        //set the grid
        ExpandableGridView gridView = (ExpandableGridView) view.findViewById(R.id.bookgrid);
        gridView.setAdapter(adapter);
        gridView.setExpanded(true);
        gridView.setFocusable(false);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
