/*
 *
 * This file is generated under this project, "programmers".
 *
 * Date  : 2018. 9. 19. 오후 8:24:07
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package programmers.stack_queue;

import java.util.Arrays;

/**
 * 
 * @since 2018. 9. 19.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        // 두 개의 입력값 길이 확인
        if (progresses == null || speeds == null || (progresses.length != speeds.length) || progresses.length > 100 || progresses.length > 100) {
            throw new IllegalArgumentException(String.format("Progresses=%s, Speeds=%s", progresses, speeds));
        }
        
        // 진도 체크
        for(int i = 0; i < progresses.length; i++) {
            if( progresses[i] > 99 || progresses[i] < 1) {
                throw new IllegalArgumentException(String.format("잘못된 작업진도값. Progresses=%s, Speeds=%s", progresses, speeds));
            }
            
            if( progresses[i] > 100 || progresses[i] < 1) {
                throw new IllegalArgumentException(String.format("잘못된 작업속도값. Progresses=%s, Speeds=%s", progresses, speeds));
            }
        }
        
        int[] deployments = new int[progresses.length];
        
        boolean postProgressed = true; // for deployment
        int completed = 0; // completed count for end condition
        int deployment = 0;
        
        int pos = 0;
        while( pos < progresses.length) {
            postProgressed = true;
            completed = 0;
            for (int i = pos; i < progresses.length; i++) {
                // 불필요한 작업 시키지 않기 ... ^^
                if (progresses[i] <100 ) {
                    progresses[i] += speeds[i];
                }
                
                if (progresses[i] > 99) {
                    if (postProgressed &= true) {
                        completed++;
                        pos++;
                    }
                    continue;
                }
                postProgressed = false;
            }
            
            // 작업이 완료된 것이 있으면 배포~
            if( completed > 0) {
                deployments[deployment++] = completed;
            }
        }
        
        int[] result = null;
        if( deployment < pos) {
            result = new int[deployment]; 
            System.arraycopy(deployments, 0, result, 0, deployment);
        }else {
            result = deployments;
        }
        
        return result;
    }

    public static void main(String[] args) {
        
        Solution s = new Solution();

        int[] deployments = s.solution(new int[] {10,9,8,7,6,5,4,3,2,1 }, new int[] { 1,2,3,4,5,6,7,8,9,10 });

        System.out.println(Arrays.toString(deployments));
    }
}
